using UnityEngine;
using System.Collections;

public class ChangeDaytime : MonoBehaviour {

    public GameObject moon;
    public GameObject daytime;
    public GameObject night;

    private float targetX;
    private float moonTargetX;
    private bool sunInCamera = false;
    private Vector3 daytimePos;
    private Vector3 nightPos;

    // Use this for initialization
    void Start()
    {
        targetX = -8f;
        moonTargetX = -2.7f;
    }

    // Update is called once per frame
    void Update()
    {

        float dt = Time.deltaTime;
        if (Input.GetKey(KeyCode.Space) && !sunInCamera)
        {
            setSunInCamera();
            daytimePos = daytime.transform.position;
            nightPos = night.transform.position;
        }

        /*if ((GetComponent<FollowHand>().GetHandClosed(1) || GetComponent<FollowHand>().GetHandClosed(0))
            && !sunInCamera)
        {
            setSunInCamera();
            daytimePos = daytime.transform.position;
            nightPos = night.transform.position;
        }*/

        if (transform.position.x >= targetX && sunInCamera)
            transform.position = transform.position + Vector3.left * 6.0f * dt;        

        if (transform.position.x <= -7 && sunInCamera)
        {
            if (moon.transform.position.x <= moonTargetX)
                moon.transform.position = moon.transform.position + Vector3.right * 6.0f * dt;
            else
                sunInCamera = false;               
        }
           
           

        if (transform.position.x <= -7 && sunInCamera)
        {
            daytime.transform.position = nightPos;
            night.transform.position = daytimePos;

        }

    }

    public void setSunInCamera()
    {
        if (transform.position.x >= -6.5f)
            sunInCamera = true;
        else
            sunInCamera = false;
    }
}
